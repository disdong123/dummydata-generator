package kr.disdong.dummydata.generator.server.core.gen.arbitrarybuildergroup

import com.navercorp.fixturemonkey.buildergroup.ArbitraryBuilderGroup
import com.navercorp.fixturemonkey.resolver.ArbitraryBuilderCandidateFactory
import com.navercorp.fixturemonkey.resolver.ArbitraryBuilderCandidateList
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import jakarta.persistence.Column
import net.jqwik.api.Arbitraries
import java.lang.reflect.AccessibleObject

class FunctionGen(
    private val entities: MutableSet<Class<*>>
) {

    fun `do`(): FunSpec {
        val builder = declareFunction()
            .createBuilderList()
            .addAllToBuilderList()
            .addReturn()

        return builder.build()
    }

    private fun FunSpec.Builder.createBuilderList(): FunSpec.Builder {
        return this.addStatement("val builderList = %T.create()", ClassName(ArbitraryBuilderCandidateList::class.java.packageName, ArbitraryBuilderCandidateList::class.java.simpleName))
    }

    private fun FunSpec.Builder.addAllToBuilderList(): FunSpec.Builder {
        entities.forEach { entity ->
            this.addToBuilderList(entity)
        }

        return this
    }

    private fun FunSpec.Builder.addToBuilderList(entity: Class<*>) {
        this.addCode("builderList.add(\n")
            .addCode("%T.of(%T::class.java)\n", ClassName(ArbitraryBuilderCandidateFactory::class.java.packageName, ArbitraryBuilderCandidateFactory::class.java.simpleName), entity)
            .addCode(".builder {\n")

        for (field in entity.declaredFields) {
            if (field.isColumn().not()) {
                continue
            }

            if (field.type != String::class.java) {
                continue
            }

            val column: Column = field.getColumn()
            this.addCode("it.set(%S, %T.strings().ofMinLength(0).ofMaxLength(%L))\n", field.name, ClassName(Arbitraries::class.java.packageName, Arbitraries::class.java.simpleName), column.length)
        }

        this.addCode("}\n")
            .addCode(");\n")
    }

    private fun FunSpec.Builder.addReturn(): FunSpec.Builder {
        this.addStatement("return builderList")

        return this
    }

    private fun AccessibleObject.isColumn(): Boolean {
        return this.isAnnotationPresent(Column::class.java)
    }

    private fun AccessibleObject.getColumn(): Column {
        return this.getAnnotation(Column::class.java)
    }

    private fun declareFunction(): FunSpec.Builder {
        return FunSpec.builder(ArbitraryBuilderGroup::class.java.declaredMethods[0].name)
            .addModifiers(KModifier.OVERRIDE)
            .returns(ClassName(ArbitraryBuilderCandidateList::class.java.packageName, ArbitraryBuilderCandidateList::class.java.simpleName))
    }
}

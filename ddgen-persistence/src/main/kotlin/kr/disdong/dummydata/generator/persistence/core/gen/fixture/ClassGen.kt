package kr.disdong.dummydata.generator.persistence.core.gen.fixture

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec

class ClassGen(
    private val outputPackageName: String,
    private val constraintClassName: String,
    private val className: String,
    private val functionGen: FunctionGen,
) {

    fun `do`(): TypeSpec {
        val typeBuilder = TypeSpec.objectBuilder(className)
            .addProperty(
                PropertySpec.builder("monkey", FixtureMonkey::class, KModifier.PRIVATE)
                    .initializer(
                        """
                        FixtureMonkey.builder()
                            .plugin(%T())
                            .registerGroup(%T())
                            .build()
                        """.trimIndent(),
                        KotlinPlugin::class,
                        ClassName(outputPackageName, constraintClassName)
                    )
                    .build()
            )

        functionGen.`do`().forEach { function ->
            typeBuilder.addFunction(function)
        }

        return typeBuilder.build()
    }
}

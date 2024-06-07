package kr.disdong.dummydata.generator.server.core.gen

import com.navercorp.fixturemonkey.buildergroup.ArbitraryBuilderGroup
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeSpec

class ClassGen(
    private val className: String,
    private val functionGen: FunctionGen,
) {

    fun `do`(): TypeSpec {
        return TypeSpec.classBuilder(className)
            .addSuperinterface(ClassName(ArbitraryBuilderGroup::class.java.packageName, ArbitraryBuilderGroup::class.java.simpleName))
            .addFunction(functionGen.`do`())
            .build()
    }
}

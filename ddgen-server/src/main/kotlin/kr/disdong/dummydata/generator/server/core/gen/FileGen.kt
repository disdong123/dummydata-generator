package kr.disdong.dummydata.generator.server.core.gen

import com.squareup.kotlinpoet.FileSpec

class FileGen(
    private val packageName: String,
    private val fileName: String,
    private val classGen: ClassGen,
) {

    fun `do`(): FileSpec {
        return FileSpec.builder(packageName, fileName)
            .addType(classGen.`do`())
            .build()
    }
}

import com.squareup.kotlinpoet.FileSpec

class FileGen(
    private val packageName: String,
    private val fileName: String,
    private val classGens: List<ClassGen>,
) {

    fun `do`(): FileSpec {
        return FileSpec.builder(packageName, fileName)
            .addTypes(classGens.map { it.`do`() })
            .build()
    }
}


import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import org.springframework.data.jpa.repository.JpaRepository

class ClassGen(
    private val entityPackageName: String,
    private val entityName: String,
    private val className: String,
) {

    fun `do`(): TypeSpec {
        return TypeSpec.interfaceBuilder(className)
            .addSuperinterface(ClassName(JpaRepository::class.java.packageName, JpaRepository::class.java.simpleName).parameterizedBy(ClassName(entityPackageName, entityName), Long::class.asClassName()))
            .build()
    }
}

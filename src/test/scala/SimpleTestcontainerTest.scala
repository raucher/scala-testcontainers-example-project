import com.dimafeng.testcontainers.JdbcDatabaseContainer.CommonParams
import com.dimafeng.testcontainers.PostgreSQLContainer
import com.dimafeng.testcontainers.scalatest.TestContainerForAll
import org.scalatest.flatspec.AnyFlatSpec
import org.testcontainers.utility.DockerImageName

class SimpleTestcontainerTest extends AnyFlatSpec
  with TestContainerForAll {

  override val containerDef: PostgreSQLContainer.Def = PostgreSQLContainer.Def(
    dockerImageName = DockerImageName.parse("postgres:14-alpine"),
    mountPostgresDataToTmpfs = true,
    commonJdbcParams = CommonParams(initScriptPath = Some("init.sql"))
  )

  "Container" should "normally start and have an open port" in {
    withContainers { pg =>
      assert(pg.container.getJdbcUrl.startsWith("jdbc:postgresql://"))
      assert(pg.container.getFirstMappedPort > 0) // host system port, exposed port is bound to (N : 5432)
    }
  }
}
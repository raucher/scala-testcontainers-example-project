import com.dimafeng.testcontainers.JdbcDatabaseContainer.CommonParams
import com.dimafeng.testcontainers.PostgreSQLContainer
import com.dimafeng.testcontainers.scalatest.TestContainerForAll
import org.scalatest.wordspec.AnyWordSpec
import org.testcontainers.utility.DockerImageName

import java.sql.DriverManager

class SimpleTestcontainerTest extends AnyWordSpec
  with TestContainerForAll {

  override val containerDef: PostgreSQLContainer.Def = PostgreSQLContainer.Def(
    dockerImageName = DockerImageName.parse("postgres:14-alpine"),
    mountPostgresDataToTmpfs = true,
    commonJdbcParams = CommonParams(initScriptPath = Some("init.sql"))
  )

  "Container" should {
    "normally start and have an open port" in {
      withContainers { pg =>
        assert(pg.container.getJdbcUrl.startsWith("jdbc:postgresql://"))
        assert(pg.container.getFirstMappedPort > 0) // host system port, exposed port is bound to (N : 5432)
      }
    }

    "execute initialization script on start" in {
      withContainers { pg =>
        val container = pg.container
        val jdbcUrl = container.getJdbcUrl
        val username = container.getUsername
        val password = container.getPassword
        val connection = DriverManager.getConnection(jdbcUrl, username, password)

        val query = "SELECT id, data FROM records"
        val (expectedId, expectedData) = (1, "record-1")

        val resultSet = connection.createStatement().executeQuery(query)

        assert(resultSet.next()) // result set is not empty
        assert(resultSet.getInt("id") == expectedId)
        assert(resultSet.getString("data") == expectedData)
      }
    }
  }
}
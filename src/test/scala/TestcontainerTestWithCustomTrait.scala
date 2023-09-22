import com.dimafeng.testcontainers.PostgreSQLContainer
import com.dimafeng.testcontainers.scalatest.TestContainerForAll
import org.scalatest.flatspec.AnyFlatSpec
import org.testcontainers.utility.DockerImageName


class TestcontainerTestWithCustomTrait extends AnyFlatSpec
  with TestContainerForAll {

  override val containerDef: PostgreSQLContainer.Def =
    PostgreSQLContainer.Def(
      dockerImageName = DockerImageName.parse(s"postgres:14-alpine"),
      mountPostgresDataToTmpfs = true
    )

  "Container" should "normally start and have an open port" in {
    withContainers { pg =>
      assert(pg.container.getJdbcUrl.contains("postgres"))
    }
  }
}



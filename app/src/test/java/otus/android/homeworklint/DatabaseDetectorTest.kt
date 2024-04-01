package otus.android.homeworklint

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test
import otus.android.homeworklint.lint.DatabaseDetector.Companion.ISSUE


class DatabaseDetectorTest {
    val lintTask = TestLintTask.lint()
        .allowMissingSdk()
        .issues(ISSUE)

    @Test
    fun `should detect func`() {
        lintTask.files(
            LintDetectorTest.kotlin(
                """
                    package test.pkg

                    class TestLintDB{
                        fun queryDatabase() {
                                return
                            }
                        }
                """.trimIndent()
            )
        )
            .run()
            .expect("""No warnings.""".trimIndent())
    }
}
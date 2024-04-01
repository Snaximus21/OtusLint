package otus.android.homeworklint.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import otus.android.homeworklint.lint.DatabaseDetector.Companion.ISSUE

class LintRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(ISSUE)

    override val api: Int
        get() = CURRENT_API

}
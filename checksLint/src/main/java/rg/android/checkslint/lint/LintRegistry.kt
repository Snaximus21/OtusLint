package rg.android.checkslint.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class LintRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(DatabaseDetector.ISSUE)

    override val api: Int
        get() = CURRENT_API

}
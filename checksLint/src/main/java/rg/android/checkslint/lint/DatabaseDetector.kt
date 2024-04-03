package rg.android.checkslint.lint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement

class DatabaseDetector : Detector(), Detector.UastScanner {

    companion object {
        private const val ID = "DatabaseOnUIThread"
        private const val BRIEF_DESCRIPTION = "Don't use main dispatcher with DB operations."
        private const val EXPLANATION = "Switch dispatcher from main thread! " +
                "DataBase operations must be use in IO dispatcher."

        val ISSUE = Issue.create(
            id = ID,
            briefDescription = BRIEF_DESCRIPTION,
            explanation = EXPLANATION,
            category = Category.CORRECTNESS,
            priority = 10,
            severity = Severity.WARNING,
            implementation = Implementation(DatabaseDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableMethodNames(): List<String> =
        listOf("query", "insert", "update", "delete", "queryDatabase")

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)
        if (isUsingMainDispatcher(node))
            context.report(
                ISSUE,
                node,
                context.getLocation(node),
                BRIEF_DESCRIPTION
            )
    }


    private fun isUsingMainDispatcher(node: UCallExpression): Boolean {
        val arguments = node.valueArguments
        if (arguments.size > 1) {
            val dispatcherArgument = arguments[1]
            val dispatcherArgumentText = dispatcherArgument.asSourceString()
            return dispatcherArgumentText.contains("Dispatchers.Main")
        }
        return false
    }
}
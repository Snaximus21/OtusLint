package otus.android.homeworklint.lint

import android.os.Looper
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
        private const val BRIEF_DESCRIPTION = "Замените Main поток на любой другой"
        private const val EXPLANATION = "Замените Main поток на любой другой. " +
                "Обращения к БД не должны выполняться на Main потоке"

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

    override fun getApplicableMethodNames(): List<String> {
        println("Method: ${object{}.javaClass.enclosingMethod?.name}")
        return listOf("query", "insert", "update", "delete", "queryDatabase")
    }

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)
        println("Method: ${object{}.javaClass.enclosingMethod?.name}")
        //if (Looper.getMainLooper() == Looper.myLooper()) {
            context.report(
                ISSUE,
                node,
                context.getLocation(node),
                BRIEF_DESCRIPTION
            )
        //}
    }
}
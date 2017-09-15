import com.fathzer.soft.javaluator.DoubleEvaluator  //http://javaluator.sourceforge.net/en/home/ is used: https://opensource.org/licenses/lgpl-3.0.html
import com.fathzer.soft.javaluator.Function

val sqrt = Function("sqrt", 1)

/**
 * Parses strings to doubles and handles them as actual math inputs
 * TODO write own math evaluator that doesn't rely on com.fathzer.soft.javaluator.DoubleEvaluator
 * There is a separate class for this evaluator because I plan to make my own implementation of this later,
 *   and the default double evaluator doesn't have a sqrt function which is what is added here
 */
class MathEvaluator: DoubleEvaluator({var params = DoubleEvaluator.getDefaultParameters(); params.add(sqrt); params}()) {

    /**
     * If the sqrt function is used, will return the square root of the function
     * Otherwise will just call the default doubleevaluator evaluate function that already has been implemented
     */
    override fun evaluate(function: Function?, arguments: MutableIterator<Double>?, evaluationContext: Any?): Double {
        if(function == sqrt){
            return Math.sqrt(arguments!!.next())
        }
        return super.evaluate(function, arguments, evaluationContext)
    }

}
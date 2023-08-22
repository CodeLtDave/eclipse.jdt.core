package org.eclipse.jdt.internal.core.search.matching;

import org.eclipse.jdt.core.compiler.CharOperation;
import org.eclipse.jdt.internal.compiler.ast.*;

public class StringLocator extends PatternLocator {

    protected boolean isDeclarationOfStringLiteralPattern;
	private StringPattern pattern;

    public StringLocator(StringPattern pattern) {
        super(pattern);

        this.isDeclarationOfStringLiteralPattern = this.pattern != null;
    }

    @Override
    public int match(ASTNode node, MatchingNodeSet nodeSet) {
        if (node instanceof StringLiteral) {
            StringLiteral stringLiteral = (StringLiteral) node;
            char[] literalValue = stringLiteral.source();

            if (matchesStringLiteral(literalValue)) {
                // Perform your matching logic and add the match to the nodeSet
                // You can determine the matching level based on your requirements
                int matchingLevel = ACCURATE_MATCH; // Change as needed
                return nodeSet.addMatch(node, matchingLevel);
            }
        }
        return IMPOSSIBLE_MATCH;
    }

    private boolean matchesStringLiteral(char[] literalValue) {
    	 if (this.pattern != null) {
             char[] expectedValue = this.pattern.getExpectedValue().toCharArray();
             return CharOperation.equals(literalValue, expectedValue);
         }

         // Return true if the string literal matches the pattern, else return false
         return false;
    }

    @Override
    protected int fineGrain() {
        return this.pattern.fineGrain;
    }

    @Override
    protected int matchContainer() {
        return ALL_CONTAINER;
    }

    // You can override other methods as needed
}

package org.eclipse.jdt.internal.core.search.matching;

import org.eclipse.jdt.core.search.SearchPattern;

public class StringPattern extends JavaSearchPattern {

    private String pattern;

    public StringPattern(String stringContent, int limitTo, int matchRule) {
        super(STRING_LITERAL_PATTERN, matchRule);
        this.pattern = stringContent;
    }

    @Override
    public boolean matchesDecodedKey(SearchPattern decodedPattern) {
        if (decodedPattern instanceof StringPattern) {
            StringPattern other = (StringPattern) decodedPattern;
            return this.pattern.equals(other.pattern);
        }
        return super.matchesDecodedKey(decodedPattern);
    }

    public String getExpectedValue() {
        return this.pattern;
    }

    @Override
    protected StringBuffer print(StringBuffer output) {
        output.append("StringPattern: ").append(this.pattern); //$NON-NLS-1$
        super.print(output);
        return output;
    }

    @Override
    public boolean isParallelSearchSupported() {
        return true;
    }
}

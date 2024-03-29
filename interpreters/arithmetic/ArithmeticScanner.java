package interpreters.arithmetic;

import java.util.ArrayList;

import static interpreters.arithmetic.TokenType.*;

class ArithmeticScanner {
    private String source;
    private int start = 0;
    private int current = 0;
    private ArrayList<Token> tokens = new ArrayList<Token>();

    ArithmeticScanner(String source) {
        this.source = source;
    }

    public ArrayList<Token> scanner() {

        while(!isAtEnd()) {
            char c = advance();
            // Handle single char tokens
            switch(c) {
                case '+': addToken(PLUS, "+"); break;
                case '-': addToken(MINUS, "-"); break;
                case '*': addToken(MULTIPLY, "*"); break;
                case '/': addToken(DIVIDE, "/"); break;
                case '(': addToken(LPAREN, "("); break;
                case ')': addToken(RPAREN, ")"); break;
                case '=': addToken(EQUAL, "="); break;
                default:
                    // Handle numbers
                    if (isDigit(c)) {
                        start=current-1;
                        number();
                    }
                    else {
                        Arithmetic.error("Token not recognized");
                    }
            }
        }
        return tokens;
    }

    private char advance() {
        return (source.charAt(current++));
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return (source.charAt(current));
    }

    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
      } 


    private boolean isAtEnd() {
        if (current == source.length()) {
            return true;
        }
        return false;
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private void addToken(TokenType type, Object value) {
        tokens.add(new Token(type, value));
    }

    private void number() {
        while (isDigit(peek())) advance();
        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) advance();
        }
        addToken(TokenType.NUMBER, Double.parseDouble(source.substring(start, current)));
    }
}
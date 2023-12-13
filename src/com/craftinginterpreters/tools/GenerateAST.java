package com.craftinginterpreters.tools;

import java.io.PrintWriter;
import java.util.Arrays;

public class GenerateAST {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: generate_ast <output directory>");
            System.exit(64);

            String outputDir = args[0];
            defineAst(outputDir, "Expr", Arrays.asList(
            "Binary   : Expr left, Token operator, Expr right",
            "Grouping : Expr, expression",
            "Literal  : Object value",
            "Unary    : Token operator, Expr right"
        ));
        }
    }
    private static void defineAst(String outputDir, String baseName, List<String> types) throws IOException {
        String path = outputDir + "/" + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        // Write boilerplate to file

        writer.println("package com.craftinginterpreters.lox;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
        writer.println("abstract class " + baseName + " {;");

        // The AST classes

        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }

        // Close
        
        writer.println("}");
        writer.close();
    }

    private static void defineType();
}
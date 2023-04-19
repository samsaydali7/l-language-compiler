package samsaydali.l.codegen;

import org.junit.jupiter.api.Test;
import samsaydali.l.app.LCompiler;
import samsaydali.l.parser.LVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaGeneratorTest {

    @Test
    void generateCodeForInput() {

        assertAll(() -> {
            LCompiler app = new LCompiler();
            LVisitor v = app.visit(readFile("example.l"));

            String expected = readFile("expected.java");

            JavaGenerator generator = new JavaGenerator();

            assertEquals(expected
                    .replaceAll("\\s", ""),
                    generator.generate(v.statements)
                    .replaceAll("\\s", ""));
        });
    }

    private static String readFile(String file) throws IOException {
        return new String(
                Files.readAllBytes(
                        Paths.get("src","test", "resources", file)
                ));
    }


}
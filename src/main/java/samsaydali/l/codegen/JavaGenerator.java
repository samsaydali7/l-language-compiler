package samsaydali.l.codegen;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import samsaydali.l.ast.Statement;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

public class JavaGenerator {

    private static final String template = "main.ftlh";

    public String generate(List<Statement> statements) throws IOException, TemplateException {

        /* Create a data-model */
        Map root = new HashMap();
        root.put("statements", statements);

        /* Get the template (uses cache internally) */
        Template temp = TemplateConfig.getConfig().getTemplate(template);

        /* Merge data-model with template */
        Writer out = new StringWriter();
        temp.process(root, out);
        out.close();

        return out.toString();
    }
}

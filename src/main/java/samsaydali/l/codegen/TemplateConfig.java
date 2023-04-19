package samsaydali.l.codegen;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.TimeZone;

public class TemplateConfig {

    private static Configuration cfg = null;

    public static Configuration getConfig() throws IOException {
        if (cfg == null) {
            /* ------------------------------------------------------------------------ */
            /* You should do this ONLY ONCE in the whole application life-cycle:        */
            /* Create and adjust the configuration singleton */
            cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(Paths.get("src","main","resources", "templates").toFile());
            // Recommended settings for new projects:
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
        }
        return cfg;
    }
}

package org.example.inventoryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

@Configuration
public class YamlConfig {

    @Bean
    public Yaml yaml() {
        LoaderOptions options = new LoaderOptions();

        options.setAllowDuplicateKeys(false);

        options.setMaxAliasesForCollections(50);

        options.setNestingDepthLimit(50);


        return new Yaml(options);
    }
}

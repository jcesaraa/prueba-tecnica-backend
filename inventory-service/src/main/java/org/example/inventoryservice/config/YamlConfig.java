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

        // Evita claves duplicadas en el mismo nivel (ataques de clave duplicada)
        options.setAllowDuplicateKeys(false);

        // Limita el número máximo de alias para colecciones (previene ataques de alias)
        options.setMaxAliasesForCollections(50);

        // Limita la profundidad de anidamiento del YAML (previene recursión excesiva)
        options.setNestingDepthLimit(50);

        // Nota: en SnakeYAML 2.x no existe setAllowAnyType ni setAllowUntrusted,
        // por lo que nos quedamos con estas opciones de seguridad disponibles.

        return new Yaml(options);
    }
}

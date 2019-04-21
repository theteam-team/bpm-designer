package com.theteam.bpmn.design;

import java.util.Map;
import java.util.function.BiConsumer;

import com.theteam.bpmn.design.App;

import io.swagger.parser.SwaggerParser;
import io.swagger.models.Path;
import io.swagger.models.Swagger;



public final class GenerateFunctions
{

    public static void generate()
    {
        
        Swagger swagger = new SwaggerParser().read(App.class.getResource("/swagger/swagger.json").toString());
        Map<String, Path> p = swagger.getPaths();


        BiConsumer<String, Path> con = new BiConsumer<String, Path>()
        {
            public void accept(String name, Path p)
            {
                System.out.println(name);
                if(p.getOperations() != null)
                {
                    p.getOperations().forEach(a -> a.getParameters().forEach(b -> System.out.println(b.getName() + " and " + b.getClass())));

                }
                
            };
        };

        p.forEach(con);

        
    }

    
}
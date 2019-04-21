package com.theteam.bpmn.design.loader;

import com.theteam.bpmn.design.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.image.Image;

/**
 * Class loader to load Images
 * Load images in memory
 * Get loaded images from memory
 */
public final class ImagesLoader
{

    // Change to Load all in folder

    

    public static HashMap<String, Image> nodeImages = new HashMap<>();

    private static Image start = 
        new Image(App.class.getResource("/images/nodes/start.png").toString());
    private static Image start_chosen = 
        new Image(App.class.getResource("/images/nodes/start_chosen.png").toString());

    private static Image end = 
        new Image(App.class.getResource("/images/nodes/end.png").toString());
    private static Image end_chosen = 
        new Image(App.class.getResource("/images/nodes/end_chosen.png").toString());

    private static Image service_task = 
        new Image(App.class.getResource("/images/nodes/service_task.png").toString());
    private static Image service_task_chosen = 
        new Image(App.class.getResource("/images/nodes/service_task_chosen.png").toString());
    
    private static Image db = 
        new Image(App.class.getResource("/images/nodes/db.png").toString());
    private static Image db_chosen = 
        new Image(App.class.getResource("/images/nodes/db_chosen.png").toString());

    private static Image external = 
        new Image(App.class.getResource("/images/nodes/external.png").toString());
    private static Image external_chosen = 
        new Image(App.class.getResource("/images/nodes/external_chosen.png").toString());

    private static Image timer = 
        new Image(App.class.getResource("/images/nodes/timer.png").toString());
    private static Image timer_chosen = 
        new Image(App.class.getResource("/images/nodes/timer_chosen.png").toString());

    private static Image script = 
        new Image(App.class.getResource("/images/nodes/script.png").toString());
    private static Image script_chosen = 
        new Image(App.class.getResource("/images/nodes/script_chosen.png").toString());

    private static Image test = 
        new Image(App.class.getResource("/images/nodes/test.png").toString());
    private static Image test_chosen = 
        new Image(App.class.getResource("/images/nodes/test_chosen.png").toString());

    private static Image triangle = 
        new Image(App.class.getResource("/images/nodes/triangle.png").toString());
    private static Image triangle_chosen = 
        new Image(App.class.getResource("/images/nodes/triangle_chosen.png").toString());

    private static Image function = 
        new Image(App.class.getResource("/images/nodes/function.png").toString());
    private static Image function_chosen = 
        new Image(App.class.getResource("/images/nodes/function_chosen.png").toString());

        

    public static void initializeImages() throws IOException
    {
        
        nodeImages.put("start", start);
        nodeImages.put("start_chosen", start_chosen);

        nodeImages.put("end", end);
        nodeImages.put("end_chosen", end_chosen);
        
        nodeImages.put("service_task", service_task);
        nodeImages.put("service_task_chosen", service_task_chosen);

        nodeImages.put("db", db);
        nodeImages.put("db_chosen", db_chosen);

        nodeImages.put("external", external);
        nodeImages.put("external_chosen", external_chosen);

        nodeImages.put("timer", timer);
        nodeImages.put("timer_chosen", timer_chosen);

        nodeImages.put("script", script);
        nodeImages.put("script_chosen", script_chosen);

        nodeImages.put("test", test);
        nodeImages.put("test_chosen", test_chosen);

        nodeImages.put("triangle", triangle);
        nodeImages.put("triangle_chosen", triangle_chosen);

        nodeImages.put("function", function);
        nodeImages.put("function_chosen", function_chosen);

        List<String> filenames = getResourceFiles("/images/nodes/");
        filenames.forEach(file -> ffile(file));
    }

    public static void ffile(String file)
    {
        System.out.println(file);
        
        String lastWord = file.substring(file.lastIndexOf("/")+1);
        
        System.out.println(lastWord);
    }

    private static List<String> getResourceFiles(String path) throws IOException 
    {
        
        List<String> filenames = new ArrayList<>();
    
        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))
            )
            
            {

            String resource;
    
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }
    
        return filenames;
    }
    
    private static InputStream getResourceAsStream(String resource)
    {
        //final InputStream in
        //        = getContextClassLoader().getResourceAsStream(resource);
    
        //return in == null ? getClass().getResourceAsStream(resource) : in;

        return App.class.getResourceAsStream(resource);
    }
    
    private ClassLoader getContextClassLoader()
    {
        return Thread.currentThread().getContextClassLoader();
    }
}
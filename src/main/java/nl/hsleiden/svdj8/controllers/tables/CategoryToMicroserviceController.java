package nl.hsleiden.svdj8.controllers.tables;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.hsleiden.svdj8.models.tables.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static java.lang.System.out;

@RestController
public class CategoryToMicroserviceController {
    private HttpURLConnection con;

    @Autowired
    public CategoryToMicroserviceController() {
    }

    private List<Category> requestToMicroService(String specific, String duty, Category category) {
        List<Category> newRequest = null;
        URL url;
        try {
            url = new URL("http://localhost:8440/microservice/category/" + specific);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("content-type", "application/json; charset=utf8");
            con.setRequestMethod(duty);
            if (!duty.equals("get")) {
                addCategoryParameter(category);
            }
            newRequest = formToCategory(readRequest(con), duty, specific);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        return newRequest;
    }

    private void addCategoryParameter(Category category) {
        Map<String, String> parameters = new HashMap<>();
        try {
            parameters.put("category", category.toString());
            con.setDoOutput(true);
            DataOutputStream out = null;
            out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(category.toString());
            out.flush();
            out.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private String readRequest(HttpURLConnection con) throws IOException {
        StringBuilder content = new StringBuilder();
        InputStream inputStream = con.getInputStream();
        Scanner scan = new Scanner(inputStream);
        if (con.getResponseCode() == 200) {
            while (scan.hasNext()) {
                content.append(scan.next());
            }

        }
        inputStream.close();
        return content.toString();
    }

    private List<Category> formToCategory(String content, String duty, String specific) {
        JsonArray jsArray;

        if (duty.equals("put") || (duty.equals("get")) && specific.equals("all")) {
            JsonObject item = new Gson().fromJson(content, JsonObject.class);
            jsArray = new JsonArray();
            jsArray.add(item);
        } else {
            jsArray = new Gson().fromJson(content, JsonArray.class);
        }
        return formListOfCategory(jsArray);
    }

    private List<Category> formListOfCategory(JsonArray jsArray) {
        ArrayList<Category> grants = new ArrayList<>();
        for (JsonElement item : jsArray) {
            grants.add((Category) new Gson().fromJson(item, Category.class));
        }
        return (List<Category>) grants;
    }

    @GetMapping(value = "/category/all")
    public List<Category> getAllCategories() {
        return requestToMicroService("all", "get", new Category());
    }

    @GetMapping(value = "/category/{id}")
    public Category getCategory(@PathVariable final Long id) {
        return requestToMicroService(id.toString(), "get", new Category()).get(0);
    }

    @PutMapping(value = "/category/{id}")
    public Category editCategory(@RequestBody Category editCategory, @PathVariable Long id) throws Exception {
        return requestToMicroService(id.toString(), "put", editCategory).get(0);
    }

    @PutMapping(value = "/category")
    public Category addCategory(@RequestBody Category newCategory) {
        return requestToMicroService("", "put", newCategory).get(0);
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable Long id) {
        requestToMicroService(id.toString(), "delete", new Category()).get(0);
    }
}

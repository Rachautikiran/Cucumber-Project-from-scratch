package context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioData =
            new HashMap<>();


    // Set Data
    public void setContext(
            String key,
            Object value) {

        scenarioData.put(key, value);
    }


    // Get Data
    public Object getContext(String key) {

        return scenarioData.get(key);
    }
}
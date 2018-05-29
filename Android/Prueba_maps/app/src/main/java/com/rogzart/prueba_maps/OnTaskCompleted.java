package com.rogzart.prueba_maps;

import java.util.HashMap;
import java.util.List;

public interface OnTaskCompleted {

    void onTaskCompleted(List<List<HashMap<String, String>>> jsonObj);
}
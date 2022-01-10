/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.Homework2.content;


import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample song content.
 */
public class CityUtils {

    // An ArrayList of Songs
    public static final List<String> City_ITEMS = new ArrayList<>();

    // The ID for the index into song titles.
    public static final String City_ID_KEY = "item_id";

    // The number of songs.
    private static final int COUNT = 4;


    /**
     * Add an item to the array.
     *
     * @param item Each song
     */
    private static void addItem(String item) {
        City_ITEMS.add(item);
    }

    static  {
        // Fill the array with songs.
        for (int i = 0; i < COUNT; i++) {
            addItem(createSongAtPosition(i));
        }
    }

    private static String createSongAtPosition(int position) {
        String newCity;
        switch (position) {
            case 0:
                newCity = "Aveiro";
                break;
            case 1:
                newCity = "Lisboa";
                break;
            case 2:
                newCity = "Porto";
                break;
            case 3:
                newCity = "Portimão";
                break;
            default:
                newCity = "Aveiro";
                break;
        }
        return newCity;
    }


}

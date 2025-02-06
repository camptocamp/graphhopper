/*
 *  Licensed to GraphHopper GmbH under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for
 *  additional information regarding copyright ownership.
 *
 *  GraphHopper GmbH licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.routing.ev;

import com.graphhopper.util.Helper;

/**
 * This enum defines the Schweizmobil edge provenance.
 * All edges that do not fit get OTHER as value.
 * The values are taken from the SwissTLM specs:
 * https://www.swisstopo.admin.ch/de/landschaftsmodell-swisstlm3d
 */
public enum SCHMObjektart {
    OTHER("Other"),
    AUSFAHRT("Ausfahrt"),
    EINFAHRT("Einfahrt"),
    AUTOBAHN("Autobahn"),
    RASTSTAETTE("Raststaette"),
    VERBINDUNG("Verbindung"),
    ZUFAHRT("Zufahrt"),
    DIENSTZUFAHRT("Dienstzufahrt"),
    M_10M_STRASSE("10m Strasse"),
    M_6M_STRASSE("6m Strasse"),
    M_4M_STRASSE("4m Strasse"),
    M_3M_STRASSE("3m Strasse"),
    PLATZ("Platz"),
    AUTOZUG("Autozug"),
    FAEHRE("Faehre"),
    M_2M_WEG("2m Weg"),
    M_1M_WEG("1m Weg"),
    M_1M_WEGFRAGMENT("1m Wegfragment"),
    M_2M_WEGFRAGMENT("2m Wegfragment"),
    MARKIERTE_SPUR("Markierte Spur"),
    M_8M_STRASSE("8m Strasse"),
    AUTOSTRASSE("Autostrasse"),
    KLETTERSTEIG("Klettersteig"),
    PROVISORIUM("Provisorium");


    private final String label;

    SCHMObjektart(String label) {
        this.label = label;
    }
    public static final String KEY = "objektart";

    public static EnumEncodedValue<SCHMObjektart> create() {
        return new EnumEncodedValue<>(SCHMObjektart.KEY, SCHMObjektart.class);
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static SCHMObjektart find(String name) {
        if (name == null || name.isEmpty())
            return OTHER;
        for (SCHMObjektart obj : SCHMObjektart.values()) {
            if (obj.label.equals(name)) {
                return obj;
            }
        }
        return OTHER;
    }
}

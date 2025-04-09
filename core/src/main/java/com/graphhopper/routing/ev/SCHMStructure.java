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

/**
 * This enum defines the Schweizmobil edge provenance.
 * All edges that do not fit get OTHER as value.
 * The values are taken from the SwissTLM specs:
 * https://www.swisstopo.admin.ch/de/landschaftsmodell-swisstlm3d
 */
public enum SCHMStructure {
    KEINE("Keine"),
    BRUECKE("Bruecke"),
    BRUECKE_MIT_GALERIE("Bruecke mit Galerie"),
    BRUECKE_MIT_TREPPE("Bruecke mit Treppe"),
    FURT("Furt"),
    GALERIE("Galerie"),
    GEDECKTE_BRUECKE("Gedeckte Bruecke"),
    IN_AUF_GEBAUEDE("in/auf Gebaeude"),
    K_W("k_W"),
    STAUDAMM("Staudamm"),
    STAUMAUER_WEHR("Staumauer, Wehr"),
    STEG("Steg"),
    TREPPE("Treppe"),
    TUNNEL("Tunnel"),
    UNTERFUEHRUNG("Unterfuehrung"),
    UNTERFUEHRUNG_MIT_TREPPE("Unterfuehrung mit Treppe");

    private final String label;

    SCHMStructure(String label) {
        this.label = label;
    }

    public static final String KEY = "structure";

    public static EnumEncodedValue<SCHMStructure> create() {
        return new EnumEncodedValue<>(SCHMStructure.KEY, SCHMStructure.class);
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static SCHMStructure find(String name) {
        if (name == null || name.isEmpty())
            return KEINE;
        for (SCHMStructure obj : SCHMStructure.values()) {
            if (obj.label.equals(name)) {
                return obj;
            }
        }
        return KEINE;
    }
}

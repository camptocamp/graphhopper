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
package com.graphhopper.routing.util;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.util.spatialrules.TransportationMode;
import com.graphhopper.util.PMap;


/**
 * @author Guillaume Beraudo
 */
public class SchmAllFlagEncoder extends SchmFlagEncoder {

    public SchmAllFlagEncoder(PMap properties) {
        super(properties);
    }

    @Override
    protected double getSpeed(ReaderWay way) {
        String land = way.getTag("land") ;
        if ("velo".equals(land) || "wander".equals(land) || "mtb".equals(land) || "skating".equals(land)) {
            return 10;
        }
        if ("tlm".equals(land)) {
            String wwString = way.getTag("ww");
            if ("1".equals(wwString)) {
                return 3;
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return "schmall";
    }

    @Override
    public TransportationMode getTransportationMode() {
        return TransportationMode.OTHER;
    }
}

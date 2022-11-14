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

import com.graphhopper.reader.ReaderNode;
import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.*;
import com.graphhopper.storage.IntsRef;
import com.graphhopper.util.PMap;

/**
 *
 * @author Guillaume Beraudo
 */
public abstract class SchmFlagEncoder extends VehicleTagParser {

    public SchmFlagEncoder(EncodedValueLookup lookup, PMap properties, String name) {
        this(
            lookup.getDecimalEncodedValue(VehicleSpeed.key(properties.getString("name", name))),
            name
        );
    }

    protected SchmFlagEncoder(DecimalEncodedValue speedEnc, String name) {
        super(null, speedEnc, name, null, null, TransportationMode.FOOT, 100);
    }

    protected abstract double getSpeed(ReaderWay way);

    @Override
    public WayAccess getAccess(ReaderWay way) {
        return WayAccess.WAY;
    }

    @Override
    public boolean isBarrier(ReaderNode node) {
        return false;
    }

    @Override
    public IntsRef handleWayTags(IntsRef edgeFlags, ReaderWay way) {
        double speed = getSpeed(way);
        avgSpeedEnc.setDecimal(false, edgeFlags, speed);
        accessEnc.setBool(false, edgeFlags, true);
        accessEnc.setBool(true, edgeFlags, true);
        return edgeFlags;
    }
}

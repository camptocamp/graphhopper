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

import com.graphhopper.reader.ReaderRelation;
import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.EncodedValue;
import com.graphhopper.routing.ev.UnsignedDecimalEncodedValue;
import com.graphhopper.storage.IntsRef;
import com.graphhopper.util.PMap;

import java.util.*;

/**
 * See https://github.com/graphhopper/graphhopper/blob/master/docs/core/create-new-flagencoder.md
 *
 * @author Guillaume Beraudo
 */
public abstract class SchmFlagEncoder extends AbstractFlagEncoder {
    public SchmFlagEncoder() {
        this(4, 1, 0);
    }

    public SchmFlagEncoder(PMap properties) {
        this(4, 1 ,0);
    }

    public SchmFlagEncoder(String propertiesStr) {
        this(new PMap(propertiesStr));
    }

    public SchmFlagEncoder(int speedBits, double speedFactor, int maxTurnCosts) {
        super(speedBits, speedFactor, maxTurnCosts);
        speedDefault = 1;
    }

    @Override
    public int getVersion() {
        return 1;
    }

    /**
     * Define the place of the speedBits in the edge flags for car.
     */
    @Override
    public void createEncodedValues(List<EncodedValue> registerNewEncodedValue, String prefix, int index) {
        // first two bits are reserved for route handling in superclass
        super.createEncodedValues(registerNewEncodedValue, prefix, index);
        registerNewEncodedValue.add(avgSpeedEnc = new UnsignedDecimalEncodedValue(
                EncodingManager.getKey(prefix, "average_speed"), speedBits, speedFactor, false)
        );
    }

    protected abstract double getSpeed(ReaderWay way);

    @Override
    public EncodingManager.Access getAccess(ReaderWay way) {
        return EncodingManager.Access.WAY;
    }

    @Override
    public IntsRef handleWayTags(IntsRef edgeFlags, ReaderWay way, EncodingManager.Access accept) {
        double speed = getSpeed(way);
        avgSpeedEnc.setDecimal(false, edgeFlags, speed);
        accessEnc.setBool(false, edgeFlags, true);
        accessEnc.setBool(true, edgeFlags, true);
        return edgeFlags;
    }

    @Override
    public String toString() {
        return "schm";
    }
}

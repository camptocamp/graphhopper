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
package com.graphhopper.routing.util.parsers;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.EnumEncodedValue;
import com.graphhopper.routing.ev.EdgeIntAccess;
import com.graphhopper.routing.ev.SCHMNetwork;
import com.graphhopper.storage.IntsRef;

import static com.graphhopper.routing.ev.SCHMNetwork.NONE;

public class SCHMNetworkParser implements TagParser {

    protected final EnumEncodedValue<SCHMNetwork> schmNetworkEnc;

    public SCHMNetworkParser(EnumEncodedValue<SCHMNetwork> schmNetworkEnc) {
        this.schmNetworkEnc = schmNetworkEnc;
    }

    @Override
    public void handleWayTags(int edgeId, EdgeIntAccess edgeIntAccess, ReaderWay readerWay, IntsRef relationFlags) {
        String schmNetworkTag = readerWay.getTag("network");
        if (schmNetworkTag == null)
            return;
        SCHMNetwork schmNetwork = SCHMNetwork.find(schmNetworkTag);
        if (schmNetwork != NONE) {
            // NOTE: the set is done only for `reverse=false` (1st arg), because
            // schmNetwork does not have the storeTwoDirections set to true. Otherwise, we
            // would
            // need to call this function again for reverse=true to store the value in the
            // reverse direction.
            schmNetworkEnc.setEnum(false, edgeId, edgeIntAccess, schmNetwork);
        }
    }
}

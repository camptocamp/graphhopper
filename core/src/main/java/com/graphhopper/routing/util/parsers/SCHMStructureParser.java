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
import com.graphhopper.routing.ev.SCHMStructure;
import com.graphhopper.storage.IntsRef;

import static com.graphhopper.routing.ev.SCHMStructure.KEINE;

public class SCHMStructureParser implements TagParser {

    protected final EnumEncodedValue<SCHMStructure> schmStructureEnc;

    public SCHMStructureParser(EnumEncodedValue<SCHMStructure> schmStructureEnc) {
        this.schmStructureEnc = schmStructureEnc;
    }

    @Override
    public void handleWayTags(int edgeId, EdgeIntAccess edgeIntAccess, ReaderWay readerWay, IntsRef relationFlags) {
        String schmStructureTag = readerWay.getTag("structures");
        if (schmStructureTag == null)
            return;
        SCHMStructure schmStructure = SCHMStructure.find(schmStructureTag);
        if (schmStructure != KEINE) {
            // NOTE: the set is done only for `reverse=false` (1st arg), because
            // schmStructure does not have the storeTwoDirections set to true. Otherwise, we
            // would need to call this function again for reverse=true to store the value in
            // the reverse direction.
            schmStructureEnc.setEnum(false, edgeId, edgeIntAccess, schmStructure);
        }
    }
}

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
import com.graphhopper.util.PMap;


/**
 * @author Guillaume Beraudo
 */
public class SchmNeutralFlagEncoder extends SchmFlagEncoder {

    public SchmNeutralFlagEncoder(PMap properties) {
        super(properties);
    }

    @Override
    protected double getSpeed(ReaderWay way) {
        String land = way.getTag("land") ;
        if ("tlm".equals(land)) {
            String rtype = way.getTag("objektart");
            if (
                    rtype.equals("10m Strasse")
                    || rtype.equals("8m Strasse")
                    || rtype.equals("6m Strasse")
                    || rtype.equals("4m Strasse")
                    || rtype.equals("3m Strasse")
                    || rtype.equals("1m Weg")
                    || rtype.equals("1m Wegfragment")
                    || rtype.equals("2m Weg")
                    || rtype.equals("2m Wegfragment")) {
                return 10;
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return "schmneutral";
    }
}

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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Guillaume Beraudo
 */
public class SchmFlagEncoderTest {
    private final EncodingManager encodingManager = EncodingManager.create("schmall,schmwander,schmvelo,schmmtb,schmneutral,schmskating");

    private final SchmWanderFlagEncoder schmwander = new SchmWanderFlagEncoder(encodingManager, new PMap());
    private final SchmAllFlagEncoder schmall = new SchmAllFlagEncoder(encodingManager, new PMap());
    private final SchmVeloFlagEncoder schmvelo = new SchmVeloFlagEncoder(encodingManager, new PMap());
    private final SchmNeutralFlagEncoder schmneutral = new SchmNeutralFlagEncoder(encodingManager, new PMap());
    private final SchmSkatingFlagEncoder schmskating = new SchmSkatingFlagEncoder(encodingManager, new PMap());
    private final SchmMtbFlagEncoder schmmtb = new SchmMtbFlagEncoder(encodingManager, new PMap());

    @Test
    public void testAccess() {
        ReaderWay way = new ReaderWay(1);
        assertTrue(schmwander.getAccess(way).isWay());
        way.setTag("highway", "residential");
        assertTrue(schmwander.getAccess(way).isWay());
    }


    @Test
    public void testWanderSpeed() {
        ReaderWay way = new ReaderWay(1);
        assertEquals(1, schmwander.getSpeed(way), 1e-1);

        way.setTag("land", "wander");
        assertEquals(10, schmwander.getSpeed(way), 1e-1);

        way.setTag("land", "tlm");
        way.setTag("ww", "1");
        assertEquals(8, schmwander.getSpeed(way), 1e-1);

        way.setTag("objektart", "3m Strasse");
        way.setTag("ww", null);
        assertEquals(3, schmwander.getSpeed(way), 1e-1);
    }

    @Test
    public void testAllSpeed() {
        ReaderWay way = new ReaderWay(1);
        assertEquals(1, schmall.getSpeed(way), 1e-1);

        way.setTag("land", "wander");
        assertEquals(10, schmall.getSpeed(way), 1e-1);

        way.setTag("land", "tlm");
        assertEquals(1, schmall.getSpeed(way), 1e-1);

        way.setTag("ww", "1");
        assertEquals(3, schmall.getSpeed(way), 1e-1);
    }


    @Test
    public void testVeloSpeed() {
        ReaderWay way = new ReaderWay(1);
        assertEquals(1, schmvelo.getSpeed(way), 1e-1);

        way.setTag("land", "velo");
        assertEquals(10, schmvelo.getSpeed(way), 1e-1);

        way.setTag("land", "tlm");

        way.setTag("objektart", "6m Strasse");
        assertEquals(3, schmvelo.getSpeed(way), 1e-1);

        way.setTag("objektart", "other");
        assertEquals(1, schmvelo.getSpeed(way), 1e-1);
    }

    @Test
    public void testNeutralSpeed() {
        ReaderWay way = new ReaderWay(1);
        assertEquals(1, schmneutral.getSpeed(way), 1e-1);

        way.setTag("land", "velo");
        assertEquals(1, schmneutral.getSpeed(way), 1e-1);

        way.setTag("land", "tlm");

        way.setTag("objektart", "6m Strasse");
        assertEquals(10, schmneutral.getSpeed(way), 1e-1);
    }

    @Test
    public void testSkatingSpeed() {
        ReaderWay way = new ReaderWay(1);
        assertEquals(1, schmskating.getSpeed(way), 1e-1);

        way.setTag("land", "skating");
        assertEquals(10, schmskating.getSpeed(way), 1e-1);
    }

    @Test
    public void testMtbSpeed() {
        ReaderWay way = new ReaderWay(1);
        assertEquals(1, schmmtb.getSpeed(way), 1e-1);

        way.setTag("land", "mtb");
        assertEquals(10, schmmtb.getSpeed(way), 1e-1);
    }
}

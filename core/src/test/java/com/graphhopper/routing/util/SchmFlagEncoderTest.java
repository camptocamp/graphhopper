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
import com.graphhopper.routing.ev.BooleanEncodedValue;
import com.graphhopper.routing.ev.DecimalEncodedValue;
import com.graphhopper.routing.ev.EncodedValue;
import com.graphhopper.routing.ev.Roundabout;
import com.graphhopper.storage.IntsRef;
import com.graphhopper.util.Helper;
import com.graphhopper.util.PMap;
import org.junit.Test;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Guillaume Beraudo
 */
public class SchmFlagEncoderTest {
    private final EncodingManager em = EncodingManager.create(
            new SchmWanderFlagEncoder(new PMap("speed_two_directions=false")),
            new SchmVeloFlagEncoder(new PMap("speed_two_directions=false")),
            new SchmSkatingFlagEncoder(new PMap("speed_two_directions=false")),
            new SchmMtbFlagEncoder(new PMap("speed_two_directions=false")),
            new SchmNeutralFlagEncoder(new PMap("speed_two_directions=false")),
            new SchmAllFlagEncoder(new PMap("speed_two_directions=false")),
            new BikeFlagEncoder(), new FootFlagEncoder()
    );

    @Test
    public void testAccess() {
        SchmFlagEncoder encoder = (SchmFlagEncoder) em.getEncoder("schmwander");
        ReaderWay way = new ReaderWay(1);
        assertTrue(encoder.getAccess(way).isWay());
        way.setTag("highway", "residential");
        assertTrue(encoder.getAccess(way).isWay());
    }


    @Test
    public void testWanderSpeed() {
        SchmFlagEncoder encoder = (SchmFlagEncoder) em.getEncoder("schmwander");
        ReaderWay way = new ReaderWay(1);
        way.setTag("highway", "residential");
        assertEquals(1, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "wander");
        assertEquals(10, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "tlm");
        way.setTag("ww", "1");
        assertEquals(8, encoder.getSpeed(way), 1e-1);

        way.setTag("objektart", "3m Strasse");
        way.setTag("ww", null);
        assertEquals(3, encoder.getSpeed(way), 1e-1);
    }

    @Test
    public void testAllSpeed() {
        SchmFlagEncoder encoder = (SchmFlagEncoder) em.getEncoder("schmall");
        ReaderWay way = new ReaderWay(1);
        way.setTag("highway", "residential");
        assertEquals(1, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "wander");
        assertEquals(10, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "tlm");
        assertEquals(1, encoder.getSpeed(way), 1e-1);

        way.setTag("ww", "1");
        assertEquals(3, encoder.getSpeed(way), 1e-1);
    }


    @Test
    public void testVeloSpeed() {
        SchmFlagEncoder encoder = (SchmFlagEncoder) em.getEncoder("schmvelo");
        ReaderWay way = new ReaderWay(1);
        way.setTag("highway", "residential");
        assertEquals(1, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "velo");
        assertEquals(10, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "tlm");

        way.setTag("objektart", "6m Strasse");
        assertEquals(3, encoder.getSpeed(way), 1e-1);

        way.setTag("objektart", "other");
        assertEquals(1, encoder.getSpeed(way), 1e-1);
    }

    @Test
    public void testNeutralSpeed() {
        SchmFlagEncoder encoder = (SchmFlagEncoder) em.getEncoder("schmneutral");
        ReaderWay way = new ReaderWay(1);
        way.setTag("highway", "residential");
        assertEquals(1, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "velo");
        assertEquals(1, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "tlm");

        way.setTag("objektart", "6m Strasse");
        assertEquals(10, encoder.getSpeed(way), 1e-1);
    }

    @Test
    public void testSkatingSpeed() {
        SchmFlagEncoder encoder = (SchmFlagEncoder) em.getEncoder("schmskating");
        ReaderWay way = new ReaderWay(1);
        way.setTag("highway", "residential");
        assertEquals(1, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "skating");
        assertEquals(10, encoder.getSpeed(way), 1e-1);
    }

    @Test
    public void testMtbSpeed() {
        SchmFlagEncoder encoder = (SchmFlagEncoder) em.getEncoder("schmmtb");
        ReaderWay way = new ReaderWay(1);
        way.setTag("highway", "residential");
        assertEquals(1, encoder.getSpeed(way), 1e-1);

        way.setTag("land", "mtb");
        assertEquals(10, encoder.getSpeed(way), 1e-1);
    }
}

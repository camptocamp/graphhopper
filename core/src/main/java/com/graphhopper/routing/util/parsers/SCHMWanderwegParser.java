package com.graphhopper.routing.util.parsers;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.BooleanEncodedValue;
import com.graphhopper.routing.ev.EdgeIntAccess;
import com.graphhopper.storage.IntsRef;

/**
 * This parser scans different OSM tags to identify ways where a cyclist has to get off her bike. Like on footway but
 * also in reverse oneway direction.
 */
public class SCHMWanderwegParser implements TagParser {

    private final BooleanEncodedValue wanderwegEnc;

    /**
     * @param bikeAccessEnc used to find out if way is oneway and so it does not matter which bike type is used.
     */
    public SCHMWanderwegParser(BooleanEncodedValue wanderwegEnc) {
        this.wanderwegEnc = wanderwegEnc;
    }

    @Override
    public void handleWayTags(int edgeId, EdgeIntAccess edgeIntAccess, ReaderWay way, IntsRef relationFlags) {
        String ww_tag = way.getTag("ww");
        boolean value = ww_tag != null && ww_tag.equals("1");
        // NOTE: the set is done only for `reverse=false` (1st arg), because
        // SchmWanderweg does not have the storeTwoDirections set to true. Otherwise, we would
        // need to call this function again for reverse=true to store the value in the
        // reverse direction.
        wanderwegEnc.setBool(false, edgeId, edgeIntAccess, value);
    }
}

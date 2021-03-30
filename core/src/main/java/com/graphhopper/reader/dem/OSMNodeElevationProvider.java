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
package com.graphhopper.reader.dem;

import com.graphhopper.storage.DAType;

public class OSMNodeElevationProvider implements ElevationProvider {

  @Override
  public double getEle(double lat, double lon) {
    throw new RuntimeException("Should not be called");
  }

  @Override
  public ElevationProvider setBaseURL(String baseURL) {
    return this;
  }

  @Override
  public ElevationProvider setDAType(DAType daType) {
    return this;
  }

  @Override
  public boolean getInterpolate() {
      return false;
  }

  @Override
  public void setInterpolate(boolean interpolate) {

  }

  @Override
  public void release() {
  }

  @Override
  public void setAutoRemoveTemporaryFiles(boolean autoRemoveTemporary) {
  }
}

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hdfs.server.datanode;

import org.apache.hadoop.hdfs.server.EntryDaemon;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * DataNode daemon.
 */
public class DataNode implements EntryDaemon {

  private AtomicBoolean running = new AtomicBoolean(true);

  public DataNode() {
    try {
      startDataNode();
    } catch (Exception ignored) {
      stop();
    }
  }

  /**
   * DataNode process entrance.
   */
  public static void main(String[] args) throws Exception {
    DataNode dataNode = instantiateDataNode();
    dataNode.join();
  }

  /**
   * Create a instance of data node.
   * @return data node.
   * @throws Exception
   */
  public static DataNode instantiateDataNode() throws Exception {
    return new DataNode();
  }

  private void startDataNode() throws Exception {

  }

  // *************************************************
  // *
  // * Implement the interface EntryDaemon.java
  // *
  // *************************************************

  public boolean isRunning() {
    return running.get();
  }

  public synchronized void join() throws Exception{
    while (isRunning()) {
      wait();
    }
  }

  public synchronized void stop() {
    running.set(false);
    notifyAll();
  }
}

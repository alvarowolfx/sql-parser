/**
 * Copyright © 2012 Akiban Technologies, Inc.  All rights
 * reserved.
 *
 * This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This program may also be available under different license terms.
 * For more information, see www.akiban.com or contact
 * licensing@akiban.com.
 *
 * Contributors:
 * Akiban Technologies, Inc.
 */

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.ExecSPSNode

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

/**
 * A ExecSPSNode is the root of a QueryTree 
 * that represents an EXECUTE STATEMENT
 * statement.    It is a tad abnormal.  Duringa
 * bind, it locates and retrieves the SPSDescriptor
 * for the particular statement.    At generate time,
 * it generates the prepared statement for the 
 * stored prepared statement and returns it (i.e.
 * it effectively replaces itself with the appropriate
 * prepared statement).
 *
 */

public class ExecSPSNode extends StatementNode 
{
    private TableName name;

    /**
     * Initializer for a ExecSPSNode
     *
     * @param newObjectName The name of the table to be created
     *
     */

    public void init(Object newObjectName) {
        this.name = (TableName)newObjectName;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        ExecSPSNode other = (ExecSPSNode)node;
        this.name = (TableName)getNodeFactory().copyNode(other.name,
                                                         getParserContext());
    }

    /** @see StatementNode#executeStatementName */
    public String executeStatementName() {
        return name.getTableName();
    }

    /** @see StatementNode#executeSchemaName */
    public String executeSchemaName() {
        return name.getSchemaName();
    }

    public String statementToString() {
        return "EXECUTE STATEMENT";
    }

}

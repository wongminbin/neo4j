/*
 * Copyright (c) 2002-2018 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.impl.index.schema;

import java.io.IOException;

import org.neo4j.kernel.api.schema.index.TestIndexDescriptorFactory;
import org.neo4j.kernel.impl.api.index.sampling.IndexSamplingConfig;
import org.neo4j.values.storable.ValueGroup;

import static org.neo4j.index.internal.gbptree.RecoveryCleanupWorkCollector.immediate;

public class LocalDateTimeUniqueIndexAccessorTest extends NativeIndexAccessorTest<LocalDateTimeIndexKey,NativeIndexValue>
{
    @Override
    NativeIndexAccessor<LocalDateTimeIndexKey,NativeIndexValue> makeAccessorWithSamplingConfig( IndexSamplingConfig samplingConfig ) throws IOException
    {
        TemporalIndexFiles.FileLayout<LocalDateTimeIndexKey> fileLayout =
                new TemporalIndexFiles.FileLayout<>( getIndexFile(), layout, ValueGroup.LOCAL_DATE_TIME );
        return new TemporalIndexAccessor.PartAccessor<>( pageCache, fs, fileLayout, immediate(), monitor, indexDescriptor, samplingConfig );
    }

    @Override
    protected LayoutTestUtil<LocalDateTimeIndexKey,NativeIndexValue> createLayoutTestUtil()
    {
        return new UniqueLayoutTestUtil<>( new LocalDateTimeLayoutTestUtil( TestIndexDescriptorFactory.uniqueForLabel( 42, 666 ) ) );
    }
}
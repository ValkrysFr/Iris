/*
 * Iris is a World Generator for Minecraft Bukkit Servers
 * Copyright (c) 2022 Arcane Arts (Volmit Software)
 *
 * This program is free software: you can redistribute it and/or modify
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
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.volmit.iris.util.nbt.io;

import com.volmit.iris.engine.data.io.Deserializer;
import com.volmit.iris.util.nbt.tag.Tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class NBTDeserializer implements Deserializer<NamedTag> {

    private final boolean compressed;

    public NBTDeserializer() {
        this(true);
    }

    public NBTDeserializer(boolean compressed) {
        this.compressed = compressed;
    }

    @Override
    public NamedTag fromStream(InputStream stream) throws IOException {
        NBTInputStream nbtIn;
        if(compressed) {
            nbtIn = new NBTInputStream(new GZIPInputStream(stream));
        } else {
            nbtIn = new NBTInputStream(stream);
        }
        return nbtIn.readTag(Tag.DEFAULT_MAX_DEPTH);
    }
}

package com.kurento.mscontrol.commons.mediamixer;

import com.kurento.mscontrol.commons.Configuration;
import com.kurento.mscontrol.commons.MediaEventNotifier;
import com.kurento.mscontrol.commons.join.JoinableContainer;
import com.kurento.mscontrol.commons.resource.ResourceContainer;

/**
 * This class is required when the application needs to mix (or add, or sum)
 * multiple media streams into a single one. Other <code>.Joinable</code> media
 * objects, like <code>NetworkConnections</code>, can be joined/unjoined to a
 * <code>MediaMixer</code>. The <code>MediaMixer</code> computes the sum of all
 * ingressing streams, and sends it out to every joined object.<br>
 * On the audio stream, the contribution of a given object is actually not
 * re-sent to this particular object (this would add a disturbing echo).<br>
 * A MediaMixer is the base for a conference service. Its usage extends to many
 * other cases, including extended bridge, coaching, background music, etc...<br>
 * A MediaMixer can send events to the application.
 */
public interface MediaMixer extends JoinableContainer, ResourceContainer,
		MediaEventNotifier<MixerEvent> {

	/**
	 * This Configuration supports simple audio mixing.
	 */
	public final Configuration<MediaMixer> AUDIO = new Configuration<MediaMixer>() {
	};

}

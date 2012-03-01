package com.kurento.commons.mscontrol.mediamixer;

import com.kurento.commons.mscontrol.Configuration;
import com.kurento.commons.mscontrol.MediaEventNotifier;
import com.kurento.commons.mscontrol.join.JoinableContainer;
import com.kurento.commons.mscontrol.resource.ResourceContainer;

public interface MediaMixer extends JoinableContainer, ResourceContainer,
		MediaEventNotifier<MixerEvent> {

	public final Configuration<MediaMixer> AUDIO = new Configuration<MediaMixer>() {
	};

}

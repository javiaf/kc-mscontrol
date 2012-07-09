package com.kurento.mscontrol.commons.mediamixer;

import com.kurento.mscontrol.commons.Configuration;
import com.kurento.mscontrol.commons.MediaEventNotifier;
import com.kurento.mscontrol.commons.join.JoinableContainer;
import com.kurento.mscontrol.commons.resource.ResourceContainer;

public interface MediaMixer extends JoinableContainer, ResourceContainer,
		MediaEventNotifier<MixerEvent> {

	public final Configuration<MediaMixer> AUDIO = new Configuration<MediaMixer>() {
	};

}

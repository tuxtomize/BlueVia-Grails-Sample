class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/bluevia/smsinbox" (controller: "bluevia", action:"inboundSMSs")
        "/bluevia/mmsinbox" (controller: "bluevia", action:"inboundMMSs")
        "/evernote/inbox" (controller: "evernote",action:"listNotebooks")

		"/"(view:"/index")
		"500"(view:'/error')
	}
}

# spheres-rf-ha

A [re-frame](https://github.com/Day8/re-frame) application designed to illustrate the systems within our solar system, relations.

## Github Pages Demo

https://manandearth.github.io/spheres-rf-ha/

Explore the different attributes of the major bodies of our solar system; First select a system at the top. Visibility is selected on the right list, note that the scale changes accordingly. Select the attributes demonstrated on the x and y axes. The scale of the graph is linear yet the representation of the size of each body is in a log scale (the differences are too great to show linearly). Hovering over a sphere pops its name and relevant selected parameters

## list of ideas and issues
https://github.com/manandearth/spheres-rf-ha/blob/gh-pages/docs/doc.org

## Development Mode

### Start Cider from Emacs:

Put this in your Emacs config file:

```
(setq cider-cljs-lein-repl
	"(do (require 'figwheel-sidecar.repl-api)
         (figwheel-sidecar.repl-api/start-figwheel!)
         (figwheel-sidecar.repl-api/cljs-repl))")
```

Navigate to a clojurescript file and start a figwheel REPL with `cider-jack-in-clojurescript` or (`C-c M-J`)

### Compile css:

Compile css file once.

```
lein garden once
```

Automatically recompile css file on change.

```
lein garden auto
```

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

```
lein clean
lein with-profile prod uberjar
```

That should compile the clojurescript code first, and then create the standalone jar.

When you run the jar you can set the port the ring server will use by setting the environment variable PORT.
If it's not set, it will run on port 3000 by default.

To deploy to heroku, first create your app:

```
heroku create
```

Then deploy the application:

```
git push heroku master
```

To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```

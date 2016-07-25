package org.excelsi.sketch;


public class Prelude implements State {
    public static final String TEXT = "Many years ago in forgotten ages, there was an ancient people#who were tired of the pain and strife#in this world, and resolved to flee.#They prayed for divine intervention,#but their gods did not respond.#Bereft of faith, they decided#to take destiny upon themselves.#They abandoned the earth#and built a path to the moon.# #One evening you find yourself face to face#with a tower of immeasurable proportion.#It extends upward seemingly forever,#fading into the dark-blue starlit sky.".replaceAll("#","\n");
    private final String _text;


    public Prelude() {
        this(TEXT);
    }

    public Prelude(final String text) {
        _text = text;
    }

    @Override public void run(final Context c) {
        c.n().poster(_text);
        c.n().pause();
        c.setState(new World());
    }
}

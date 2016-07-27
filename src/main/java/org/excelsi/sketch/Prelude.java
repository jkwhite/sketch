package org.excelsi.sketch;


public class Prelude implements State {
    public static final String TEXT = "Many years ago in forgotten ages,#there lived an ancient people#who achieved mastery over all the earth#and built many things of wonder.##But their most wondrous achievement#was the construction of the Tower -#a structure of immeasurable proportion#linking together the earth and its moon.##Legends say that those ancient people#grew capricious and careless in their mastery,#and in despair, they abandoned the earth#when it first started to die.##Now with its mythical creators long vanished#and the means to control it lost to time,#the crumbling Tower stands dark and silent,#yet still reaching upward seemingly forever.##But there are those who say that the Tower#can still be traversed, and that the key#to reviving the earth lies at the other end#of that labyrinthine aethereal path..."
        .replaceAll("#","\n");
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

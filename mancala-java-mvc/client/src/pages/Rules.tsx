export const Rules = () => {
    return <div className="flex h-full items-center justify-center bg-transparent">
        <article className="prose h-min w-full p-6 text-justify text-2xl">
            <h1 className="text-center">Rules</h1>
            <p>
                The object of this game is to collect as many gems in your Kalaha as possible.
                (Player 1's Kalaha is the rightmost store, Player 2's Kalaha is the leftmost store.)
                You and your opponent will take turns to move the gems according to the following rules.
            </p>
            <ol>
                <li>
                    You can only move the gems on your side.
                    (Player 1's side is down, Player 2's side is up.)
                </li>
                <li>
                    Each time you move, you pick up all the gems in a pit and distribute
                    them in a counterclockwise direction to the next pit.
                </li>
                <li>
                    If the last gem of a move landed on your Kalaha, then you can move again.
                </li>
                <li>
                    If the last gem of a move landed on an empty pit on your side and there
                    are some gems in the opposite pit, then the gems in the two pits will be
                    captured in your Kalaha.
                </li>
                <li>
                    If it is your turn, but there are no more stones on your side the game ends.
                    The player with the most stones at their side wins the game.
                </li>
            </ol>

        </article>
    </div>
}
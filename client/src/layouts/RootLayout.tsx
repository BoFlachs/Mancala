import { Outlet, useLocation } from "react-router-dom";
import logo from "../assets/logo.jpg";
import { NavButton } from "../components/NavButton";
import { useMancalaGame } from "../contexts/MancalaGameContext";

export const RootLayout = () => {
    const { pathname } = useLocation();
    const { gameState } = useMancalaGame();
    

    return <div className="flex flex-col h-screen ">
        <header className="bg-sogyo shadow-lg flex flex-row p-4">
            <img src={logo} />
            <nav className="pt-4 flex-1 flex flex-row justify-center gap-2">
                <NavButton to="/" text="Play" isActive={pathname === "/"} />
                {gameState && <NavButton to="/newGame" text="New Game" isActive={false} />}
                <NavButton to="/rules" text="Rules" isActive={pathname === "/rules"} />
                <NavButton to="/about" text="About" isActive={pathname === "/about"} />
            </nav>
        </header>
        <main className="flex-1 bg-[#f0f7da]">
            <Outlet />
        </main>
    </div>
};

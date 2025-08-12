import React from 'react'
import { Pause, Play } from 'lucide-react'
import { Button } from './ui/button'

const services = [
  {
    gradient: "from-gray-600 to-gray-800",
    icon: "Arcade",
    title: "Game on",
    description: "100+ incredible games"
  },
  {
    gradient: "from-green-600 to-blue-600",
    icon: "Fitness+",
    title: "Strength with Gregg",
    description: "",
    hasButton: true,
    buttonText: "Watch now"
  },
  {
    gradient: "from-pink-500 to-purple-600",
    icon: "Music",
    title: "A-List Pop",
    description: "The biggest songs right now"
  },
  {
    gradient: "from-red-600 to-blue-800",
    icon: "Arcade",
    title: "Balatro+",
    description: "Card-based roguelike"
  },
  {
    gradient: "from-yellow-500 to-orange-600",
    icon: "TV+",
    title: "Original Shows",
    description: "Award-winning content"
  }
]

export function FlowingServices({ isScrollPaused, toggleScroll }) {
  return (
    <section className="py-16 bg-white overflow-hidden relative">
      <div className="max-w-none">
        <div className={`flex gap-4 animate-scroll ${isScrollPaused ? "animation-paused" : ""}`}>
          {/* First set of items */}
          {services.map((service, index) => (
            <ServiceCard key={index} {...service} />
          ))}
          
          {/* Duplicate set for seamless loop */}
          {services.map((service, index) => (
            <ServiceCard key={`duplicate-${index}`} {...service} />
          ))}
        </div>
      </div>

      {/* Pause/Play Button */}
      <button
        onClick={toggleScroll}
        className="absolute bottom-4 right-4 bg-black/20 backdrop-blur-sm text-black p-2 rounded-full hover:bg-black/30 transition-all duration-200 z-20 border border-gray-300"
        aria-label={isScrollPaused ? "Resume scrolling" : "Pause scrolling"}
      >
        {isScrollPaused ? <Play className="w-4 h-4" /> : <Pause className="w-4 h-4" />}
      </button>
    </section>
  )
}

function ServiceCard({ gradient, icon, title, description, hasButton, buttonText }) {
  return (
    <div className={`min-w-[400px] h-[300px] relative bg-gradient-to-br ${gradient} flex items-center justify-center rounded-2xl mx-2`}>
      <div className="absolute inset-0 bg-black/20 rounded-2xl"></div>
      <div className="relative z-10 text-center text-white p-8">
        <div className="flex items-center justify-center mb-4">
          <svg className="w-6 h-6 mr-2" fill="currentColor" viewBox="0 0 24 24">
            <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2z" />
          </svg>
          <span className="text-lg font-medium">{icon}</span>
        </div>
        <h3 className="text-2xl font-bold mb-2">{title}</h3>
        {description && <p className="text-sm opacity-80">{description}</p>}
        {hasButton && (
          <Button className="bg-white text-black hover:bg-gray-100 px-6 py-2 rounded-full text-sm">
            {buttonText}
          </Button>
        )}
      </div>
    </div>
  )
} 